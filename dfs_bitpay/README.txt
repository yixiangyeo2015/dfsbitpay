========================================================================================================================
HOW TO USE DFSBITPAY API
========================================================================================================================

POST request to https://dfsbitpay.herokuapp.com/pay
   - Parameters:
	a) id
	b) price
	c) currency
	d) notify_url 
	e) merchant_id
	f) return_url

========================================================================================================================
HOW TO REDEPLOY
========================================================================================================================

1. Setup Java, MYSQL, and apache Tomcat
2. Build the project in maven
3. Deploy the app in apache tomcat by war or jar

========================================================================================================================
PAIRING OF PRIVATE KEY (IF NECESSARY)
========================================================================================================================

1. In the event if authentication failed from the bitpayment receiving account, log in into bitpay with the following: 

	Bitpay account (Receiving account)

	Username: yixiang.yeo@dfs.com
	Password: Abcd123$

2. Read the error message on the console as steps will be provided to pair the private key of the account


