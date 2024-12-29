#JDBC (<span style="background:#d4b106">Java Database Connectivity</span>) é uma API (Application Programming Interface) do Java que <span style="background:#d4b106">permite a interação com banco de dados</span>, sejam eles #relacionais, planilhas ou arquivos de texto.  Isso permite que as aplicações realizem operações como conexão, consultas, atualizações, e transações com bases de dados. 

## Cenários onde o JDBC é bastante utilizado
1. **Aplicações corporativas:** em ambientes onde o acesso direto a banco de dados é necessário, como sistemas de gerenciamento, ERP, ou aplicações legadas. 
2. **Frameworks ORM:** muitas tecnologias de mapeamento objeto-relacional (ORM), como o Hibernate e JPA, usam JDBC em seu núcleo para interagir com banco de dados. Esses frameworks abstraem boa parte da complexidade, mas, por trás, dependem de JDBC.
3. **Ambientes com controle total sobre o SQL:** em cenários onde os desenvolvedores precisam de controle total sobre as consultas SQL (como otimizações específicas, controle de performance ou complexidade), o JDBC é frequentemente utilizado diretamente, sem a camada de abstração de frameworks ORM.

O JDBC permite programarmos a nossa aplicação de maneira única. Em nossa aplicação, nós instalamos um driver do JDBC, ou JDBC Driver Manager. O driver do #JDBC vai converter o código JAVA <span style="background:#d4b106">em bancos de dados específicos</span>:
- MySQL;
- PostgreSQL;
- Oracle;
- Sybase;
