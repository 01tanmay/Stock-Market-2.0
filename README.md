Services:
1. Eureka
   => Port : 8761
   => Url  : http://localhost:8761/

2. Zuul
   => Port : 8080
   => Swagger url  : http://localhost:8080/swagger-ui.html

3. User - Authentication
   => Port : 8081

4. StockService
   => Port : 8082
   => Swagger url  : http://localhost:8082/swagger-ui.html 

5. CompanyService
   => Port : 8083
   => Swagger url  : http://localhost:8083/swagger-ui.html 

6. Angular
   => Port : 4200
   => url  : http://localhost:4200/login

7. Logstash
   => Port : 9600
   => Url  : 
   => Steps: 1) Path: C:\logstash-7.10.2\bin 
             2) Command : logstash -f logstash.conf
			 
8. Elastic Search
   => Port : 9200
   => Url  : http://localhost:9200/
   => Steps: http://localhost:9200/_cat/indices
   
9. Kibanna
   => Port : 5601
   => Url  : http://localhost:5601
   => Steps: 1) Go to C:\Softwares\Java Setup\kibana-7.10.2-windows-x86_64\bin => kibanna.bat
             2) Enable in config file => elasticsearch.hosts: ["http://localhost:9200"]

10. Actuator
   => Port : 8082 , 8083
   => Url  : http://localhost:8083/actuator (Company Service), http://localhost:8082/actuator (Stock Service)

11. Promethus
   => Port : 9090
   => Url  : http://localhost:9090/
   => Steps: 1) C:\Users\User\Documents\prometheus-2.28.1.windows-amd64
             2) prometheus.exe --config.file=company-app.yml

12. Grafana
   => Port : 3123  [3000 is the default, but some other service is running]
   => Url  : http://localhost:3123
   => Steps: C:\Users\User\Downloads\grafana-8.0.5\bin => grafana-server.exe

13. sonarcube
   => Port : 9000
   => Url  : http://localhost:9000/
   => Steps: 1) Go to C:\sonarqube\bin\windows-x86-64 => StartSonar.bat
             2)
			 
===================
{
  "companyCeo": "Test",
  "companyCode": "tst",
  "companyName": "Test Ltd",
  "companyTurnOver": 50012,
  "companyWebsite": "www.tst.com",
  "stockExchanges": [
    {
      "price": 352,
      "stockCreatedDate": "21/08/2020"
    }
  ]
}
==========================================

Angular AWS S3 Static Webhost url: 
http://stockmarket-ui1.s3-website.ap-south-1.amazonaws.com/

==========================================
Company Service: 
http://13.59.147.29:8083/api/v1.0/market/company/register
http://13.59.147.29:8083/api/v1.0/market/company/info/{companyCode}
http://13.59.147.29:8083/api/v1.0/market/company/getall
http://13.59.147.29:8083/api/v1.0/market/company/delete/{companyCode}

Stock Service:
http://13.59.147.29:8082/api/v1.0/market/stock/add/{companyCode}done (stock-service)
http://13.59.147.29:8082/api/v1.0/market/stock/latest/{companyCode}done (stock-service)
http://13.59.147.29:8082/api/v1.0/market/stock/delete/{companyCode}done (stock-service)
http://13.59.147.29:8082/api/v1.0/market/stock/add/new/{companyCode}done (stockservice)
http://13.59.147.29:8082/api/v1.0/market/stock/get/{companyCode} (stockservice)

Authentication Service:
http://13.59.147.29:8081/api/auth/signin
http://13.59.147.29:8081/api/auth/signup

vpc: vpc-fb62f990
security groupID: stock--8851

API Gateway URL's:

Add Stock: https://y032zgh7qg.execute-api.us-east-2.amazonaws.com/prod/{companyCode} -> Post
Delete Stock : https://y032zgh7qg.execute-api.us-east-2.amazonaws.com/prod/{companyCode} -> Delete
Retrieve latest Stock:   https://y032zgh7qg.execute-api.us-east-2.amazonaws.com/prod/{companyCode} -> Get

Add Stock For new Company:  https://y5xdu61xe4.execute-api.us-east-2.amazonaws.com/prod/{companyCode} -> Post
Retrieve Stock by Date:  https://y5xdu61xe4.execute-api.us-east-2.amazonaws.com/prod/{companyCode}

Register Company:  https://wwvmzopnwl.execute-api.us-east-2.amazonaws.com/prod/company-service
GetCompanyDetails By CompanyCode: https://wwvmzopnwl.execute-api.us-east-2.amazonaws.com/prod/{companyCode} --> Get
DeleteCompanyBy CompanyCode: https://wwvmzopnwl.execute-api.us-east-2.amazonaws.com/prod/{companyCode} -> Delete
GetAllCompanyDetails :  https://wwvmzopnwl.execute-api.us-east-2.amazonaws.com/prod/company-service

Signin: https://2isscj877k.execute-api.us-east-2.amazonaws.com/prod/authservice
Signup: https://2isscj877k.execute-api.us-east-2.amazonaws.com/prod/auth-service
