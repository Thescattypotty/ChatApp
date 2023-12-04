


Files : *.java


Database : 
    {User(int id , String username , String password)}
    {Profile(int id , int userId , String Firstname , String LastName ,int age)}
    {Discussion(int id , int user1 , int user2 , String CreatedAt , String lastUpdated)}
    {Message(int id , int DiscussionId , int senderId(referent to userid) , String Content , String SentAt)}

Client to Server & Server to Client (vis versa)



I Should Treat This Communication between Both Client - Server - Client 
  


OnConnection for the User , i will launch a Function that get all his informations (from database) & print it on Controllers on need 

The User onConnection --> directly Becaume a Client For the Server (build a connection)


I will use a Thread To Manage Discussion's and Messages for Client - Server 


I need to Build a Service for Client On connection (extends Thread) { User user } this will be run on the background to access the User(Client)
    From all the Code ( i need to build an Exception UserNotLogin )


I need to build a Service for DiscussionController that manage Messages between only to clients & use it in the Thread Manage Discussion's and Messages for
    Client - Server {Discussion discussion , that have user1 & user2 }


I need to reBuild Messages Database to accept all Type Messages (for now i will use only Text)


