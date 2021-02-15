<h1>This is a Challenge Solution for Santander</h1>

<h3> Solution Feature</h3>

  -   This is Developed using Spring Framework
  
  -   Multiple Queries can be done Simultaneously
  
  -   As of now, only 1 Thread can update the price - This can be modified

<h3>Design Decisions</h3>
<h5>Why ConcurrentHashMap is used instead of ReadwriteLock</h5>

  - Concurrent HashMap over ReadWriteLock as from problem we are only publishing the last available spot price

  - There is a possibility especially if the read and write is happening on the same combo where we may get a stale price
  
  - If however is a need to an accurate latest data - we can switch to readwrite Lock 
  
  - The Readwrite lock will have performance implications as we will be blocking all read threads when the write is happening 

<h5>For ConcurrentHashMap Why none of the parameters was initialised</h5>
  
  - This is dependent upon the load and hence best left in default mode
  

<h3>The Solution can be tested as below</h3> 
  
  - Do Maven build and run SpotPriceApplication - Go to browser and open url http://localhost:3001/spotPrice?instrumentName=EUR/GBP
  
  - Run Test class SpotPriceApplicationTests that tests against the Controller - This test also inputs some spot-prices that is used in test
  
  - Run Integration Endpoint test using Test class SpotPriceEndpointIntegrationTest - This test also inputs some spot-prices that is used in test
  
  

