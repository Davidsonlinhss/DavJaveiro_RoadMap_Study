Quando temos opções de fluxo a serem tratadas com base no valor de uma variável, ao invés de várias estruturas #if-else encadeadas, alguns preferem utilizar a estrutura #switch-case.

**Estrutura if-else encadeada**:
```Java
if (dayOfWeek == 1) {  
        day = "domingo";  
    } else if (dayOfWeek == 2) {  
        day = "segunda";  
    } else if (dayOfWeek == 3) {  
        day = "terça";  
    } else if (dayOfWeek == 4) {  
        day = "quarta";  
    } else if (dayOfWeek == 5) {  
        day = "quinta-feira";  
    } else if (dayOfWeek == 6) {  
        day = "sexta-feira";  
    } else if (dayOfWeek == 7) {  
        day = "sábado";  
    } else {  
        System.out.print("Dia da semana inválido");  
    }  
    System.out.print("Dia da semana:" + day);  
}
```

**Estrutura Swtich-case:**
```Java
switch (dayOfWeek) {  
    case 1:  
        day = "domingo";  
        break;    
    case 2:  
        day = "terça";  
        break;    
    case 3:  
        day = "quarta";  
        break;    
    case 4:  
        day = "quinta";  
        break;    
    case 5:  
        day = "sexta";  
        break;    
    case 6:  
        day = "sábado";  
        break;    
    default:  
        day = "Valor inválido";  
        break;}  
  
System.out.print("Dia da semana:" + day);
```

