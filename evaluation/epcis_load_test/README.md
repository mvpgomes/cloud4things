# EPCIS Repository Load Test
With this test we want to test the EPCIS Repository load capacity by using  [Jmeter](http://jmeter.apache.org/). Jmeter is configured to send HTTP requests to the EPCIS Repository that is running in the cloud - AWS EC2. Each request contains a XML description of an event that must be recorded in the application database. In order to simulate a real scenario, we use the data obtained from the Rec&Play sessions.

Until the date we reproduce each session in which we simulate 1 to 5 users that make the requests simultaneously. The obtained results are in the ``results`` directory. In the ``app`` directory there is a Scala application developed to collect the metrics from EC2 - via Cloudwatch API - and calculate the Average, Minimum and Maximum value of each metric.

In our Wiki page we will present a more detailed evaluation of the obtained performance results.
