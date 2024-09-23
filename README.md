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
   => Steps: 1) docker run -p 9090:9090 -v /C:/prometheus.yml prom/prometheus
			 2) select any metric and execute
 
12. Grafana
   => Port : 3123  [3000 is the default, but some other service is running]
   => Url  : http://localhost:3000
   => Steps: 1) docker run -d --name=grafana-fse -p 3000:3000 grafana/grafana
			 2) admin/admin

13. sonarcube
   => Port : 9000
   => Url  : http://localhost:9004/
   => Steps: 1) C:\sonarqube\bin\windows-x86-64\ => StartSonar.bat
             2) clean org.jacoco:jacoco-maven-plugin:prepare-agent install
			 3) sonar:sonar
			 
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


Stock Market 2.0 is a Java-based web application designed to provide users with real-time stock market data and analysis tools.
Developed using Java technologies such as Spring Boot & Angular this project aims to offer a comprehensive platform for tracking stocks, analyzing market trends, 
and making informed investment decisions.

Key Features:
Real-time Data: Utilizes APIs to fetch real-time stock market data from leading financial sources, ensuring accuracy and timeliness.
User Authentication: Implements secure user authentication and authorization functionalities to protect user data and ensure privacy.
Interactive Dashboard: Offers an intuitive and user-friendly dashboard where users can view their portfolio, watchlist, and personalized market insights.
Advanced Analytics: Provides a range of analytical tools and charts to help users analyze stock performance, track market trends, and make data-driven investment decisions.
Customizable Alerts: Enables users to set up personalized alerts for price changes, news updates, and other market events, ensuring they stay informed and proactive.
Technologies Used:

Java: Backend development using Java programming language.
Spring Boot: Framework for building robust and scalable Java applications.
Thymeleaf: Server-side Java template engine for dynamic web content generation.
Bootstrap: Frontend framework for responsive and mobile-first web development.
RESTful APIs: Integration with external APIs to fetch real-time stock market data.
Future Enhancements:

Implement additional features such as stock screener, backtesting tools, and social integration.
Enhance user experience with responsive design, mobile app development, and multi-platform support.
Introduce machine learning algorithms for predictive analytics and personalized recommendations.
Contributions:

This project welcomes contributions from the developer community. Feel free to submit pull requests, report issues, or suggest new features.
License:

This project is licensed under the MIT License, granting users the freedom to use, modify, and distribute the software.
