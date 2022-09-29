# Log Uploader

## O que é?
    O sistema web Log Uploader é focado no upload de um arquivo de logs, para popular um banco de dados. A inserção no banco pode ser feita de forma manual (CRUD padrão)
    ou de forma automática (envia um arquivo, o sistema percorre todos os dados e os insere no banco).
    
## Documentação:
     A documentação da API foi gerada com Swagger, com o projeto rodando, acesse: http://localhost:8080/swagger-ui.html
 
## Testes:
     Os testes unitários foram feitos com JUnit, estão em: log_uploader/src/test/java/keyrus/desafio_crud/DesafioApplicationTests.java
 
 ## Popular banco por arquivo:
     Acesse a página HTML localizada em: http://localhost:8080/fileUpload.html (log_uploader/src/main/resources/static/fileUpload.html), ou use algum programa para teste de APIS (Postman, por exemplo).
     Selecione o arquivo para ser enviado, e pronto! Os registros serão adicionados ao banco de dados.
     (O arquivo pré configurado para testes está localizado em: log_uploader/logFiles/logs.csv)
     
 ## Crud manual:
 ### Post:
    A inserção dos dados de forma manual é feita a partir dos seguintes parâmetros: Data, Ip, Request, Status, UserAgent.
    Exemplo: Data: 15-10-2015 10:20:56.113, Ip: 71.195.9.207, Request: get, Status: 200, UserAgent: "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0".
    
 ### Put:
     Envia o id do registro previamente criado, com informações diferentes.
  
  ### Delete:
    O delete é feito a partir do ID registrado automaticamente no banco
    
  ### Get:
    Para a visualização dos registros, existem 4 formas: Visualizar tudo, ou com o filtro de Id, Endereço Ip ou User Agent, 
