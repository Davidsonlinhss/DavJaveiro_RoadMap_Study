**OBJETIVOS**
1. Vamos construir um encurtador de URL;
2. Utilizaremos os serviços da AWS como AWS lambda + S3;
3. Além de integrar os serviços com uma API gateway;

![[Encurtador de URL Java - Dia 1.png]]
A ideia por trás do sistema é permitir que usuários criem URLs curtas que redirecionem para URLs originais com um tempo de expiração configurável. O sistema é composto por duas funções #lambda: 
1. A primeira função será responsável por **gerar e armazenar** os links encurtados em um **bucket S3** (são como contêiner lógico onde armazenamos os dados), junto com informações como a URL original e o tempo de expiração; 
2. A segunda função gerencia o redirecionamento, verificando o código da URL encurtada e validando se a URL ainda está dentro do prazo de expiração antes de redirecionar o user;

---
## Funções Serverless
É um modelo de computação em nuvem...

Funções #Serverless são unidades de código que executam tarefas específicas sem a necessidade de configurar ou gerenciar servidores explicitamente. Elas fazem parte do paradigma de **Computação Serverless**, onde <span style="background:#fff88f">o provedor de nuvem lida com toda a infraestrutura subjacente</span>, permitindo que os desenvolvedores foquem apenas na lógica da aplicação.  (CLÁSSICA)...

### Características Principais

1. **Gerenciamento Automático de Infraestrutura**:
    - O provedor de nuvem (como AWS, Azure, ou Google Cloud) gerencia os servidores, escalabilidade, balanceamento de carga, etc.
    - O desenvolvedor não precisa se preocupar com a administração ou configuração do servidor.

2. **Baseadas em Eventos**:
- Funções serverless são acionadas por #eventos, como uma <span style="background:#d4b106">requisição HTTP</span>, mensagens em filas, alterações em bancos de dados ou uploads de arquivos.
- Exemplos:
    - Um upload em um bucket S3 (AWS) dispara uma função Lambda para processar o arquivo.
    - Uma função é acionada quando uma #API recebe uma chamada.

3. **Escalabilidade Automática**:
- Elas escalam automaticamente com base na demanda.
- Se houver 1 ou 1.000.000 de requisições, a infraestrutura ajusta o número de instâncias conforme necessário.

4. **Modelo de Pagamento por Uso**:
    
    - Você paga apenas pelo tempo de execução da função, geralmente medido em milissegundos.
    - Não há custo quando a função está inativa.
5. **Independência de Linguagem**:
    
    - Você pode escrever funções em várias linguagens de programação, como #Java (<span style="background:#ff4d4f">nosso objetivo</span>), Python, Node.js, Go, C#, entre outras, dependendo do provedor de nuvem.


---
A nossa função Lambda fica dormente enquanto não há uso, ela fica em um estado ocioso...

A nossa função possuí uma espécie de tempo adicional necessário para iniciar uma função Lambda quando ela é chamada pela primeira vez ou após ficar inativa por um certo período. Esse fenômeno ocorre porque o AWS precisa criar e inicializar o ambiente de execução para a função antes que o código comece a ser executado...

---
**DEPENDÊNCIAS AWS**
 As dependências utilizadas fazem parte do desenvolvimento de funções AWS Lambda em Java, fornecendo classes e interfaces essenciais para implementar e gerenciar funções Lambda, além de suporte para logging.
 
1. <span style="background:#affad1">aws-lambda-java-core</span>: Essa dependência fornece as #classes e #interfaces principais para criar e executar as funções Lambda em Java;
- Incluir a #interface #RequestHandler, que define o método #handleRequest para <span style="background:#d4b106">processar eventos de entrada;</span>
- Oferece suporte para lidar com o contexto de execução da função, como logs e informações sobre a execução atual, por meio da classe #Context;

2. <span style="background:#affad1">aws-lambda-java-log4j2</span>: é uma biblioteca de logging poderosa e configurável que facilita o gerenciamento de logs de aplicações.
- Permite configurar logs detalhados em funções Lambda, incluindo níveis (INFO, DEBUG, ERROR, etc).
- Integra-se ao sistema de logs do AWS Lambda, que direciona as mensagens para o **Amazon CloudWatch Logs**

**RESUMINDO**: Essas dependências são amplamente utilizadas no desenvolvimento de aplicações serverless na AWS, permitindo criar funções Lambda eficientes e com boa capacidade de depuração.

---
## Amazon S3 (Simple Storage Service)
É um serviço de armazenamento escalável e altamente durável da AWS, utilizado para #armazenar e #recuperar **dados** de forma confiável e segura a qualquer momento, de qualquer lugar da internet. Ele é projetado para lidar com volumes de dados de qualquer tamanho e tipo.

### Principais Casos de Uso do Amazon S3
1. **Armazenamento de Arquivos e Dados**
- Ideal para armazenar arquivos de qualquer tamanho, como imagens, vídeos, documentos, backups, logs e outros tipos de dados não estruturados;
2. **Backup e Recuperação de Dados**
- Usado como solução de backup para proteger informação importante de perda de dados, com suporte para recuperação eficiente;
3. **Armazenamento de Dados para Análise**
- Funciona como um repositório central para grandes volumes de dados que podem ser processados posteriormente por serviço como Amazon Athena, Amazon Redshift e Amazon EMR;
4. **Hospedagem de Sites Estáticos**
- Permite hospedar sites simples diretamente no S3 (HTML, CSS, JavaScript), sem a necessidade de servidores.
5. **Distribuição de Conteúdo e Mídia**
- Armazena e distribui arquivos de mídia (vídeos, áudios, imagens) com alto desempenho, especialmente em conjunto com o Amazon CloudFront (CDN).
6. **Armazenamento de Dados para Aplicativos**
    
    - Funciona como uma solução de armazenamento para aplicativos web e móveis que precisam armazenar arquivos de usuários ou dados gerados.
7. **Arquivamento de Dados de Longo Prazo**
    
    - Oferece classes de armazenamento como o S3 Glacier, que são econômicas para arquivar dados que não precisam de acesso frequente.
8. **Logs e Monitoramento**
    
    - Usado para armazenar logs de acesso, métricas de sistemas e dados de auditoria.

---

Vamos utilizar o Amazon S3 para armazenar as <span style="background:#d4b106">URLs</span> e os <span style="background:#d4b106">Expiration Time</span>. 
- No contexto do Amazon S3, o Expiration Time refere-se a configuração que define o período de tempo após o qual um #objeto armazenado em um #bucket é <span style="background:#d4b106">automaticamente excluído</span>.

### **Por Que Usar o Expiration Time?**
1. **Redução de Custos:**
    - <span style="background:#d4b106">Remove automaticamente objetos antigos ou que não são mais necessários</span>, liberando espaço e economizando custos.
2. **Automatização:**
    - <span style="background:#d4b106">Elimina a necessidade de deletar manualmente arquivos obsoletos</span>.
3. **Gerenciamento de Conformidade:**
    - Ajuda a <span style="background:#d4b106">garantir que dados temporários sejam removidos após o período exigido por regulamentos ou políticas internas</span>.

- O Amazon S3 foi projetado para 99,999999999% (11 noves) de durabilidade e armazena dados de milhões de clientes para empresas em todo o mundo.
Quando carregamos um arquivo (objeto) no S3, ele é automaticamente replicado para **múltiplos servidores em diferentes zonas de disponibilidade** (data centers separados fisicamente) dentro de uma região da AWS. Mesmo após falhas em um determinado servidor, os nossos dados estão disponíveis em outras réplicas.

Os dados são armazenados com #redundância interna dentro de cada data center usando técnicas avançadas de dispersão de dados e verificação de integridade. 

O S3 realiza verificações regulares da integridade dos dados. Se algum fragmento de dados apresentar corrupção, ele será automaticamente reparado utilizando as cópias redundantes.

### **Por Que Isso é Importante para Empresas?**
- **Dados Empresariais Críticos:** Empresas dependem do S3 para armazenar informações sensíveis, como registros financeiros, dados de clientes e backups.
- **Redução de Riscos:** A durabilidade de 11 noves minimiza o risco de perder dados valiosos.
- **Conformidade:** Atende requisitos de conformidade e auditoria para muitos setores, como saúde e finanças.

---
**ATENÇÃO NA HORA DE CRIAR UM BUCKET**
O nome de um Bucket deve ser único entre todas as contas da AWS... 

Após a criação do nosso Bucket, vamos conectar a nossa função Lambda ao bucket...
