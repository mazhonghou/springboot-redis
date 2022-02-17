# springboot版的抽奖系统
### 利用redis的sadd spop smembers命令实现抽奖功能。 


![image](https://user-images.githubusercontent.com/31470330/154205346-c64a5450-c8c7-45e9-b761-2d6aa085f570.png)

# prize.properties 奖品初始化

prize.first-prize=iphone13  
prize.first-number=1  

prize.second-prize=小米平板  
prize.second-number=3  

prize.third-prize=kindle  
prize.third-number=5  

#由于每个人最多只能获得一项奖品，故参与抽奖人数应大于全部奖品数量  
prize.join-staff=张三,李四,王五,赵六,马七,王八,刘九,杨十,邓一,甄二  
