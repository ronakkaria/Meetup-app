package controllers;

import play.Logger;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class MyWebSocketActor extends UntypedActor {

	static int maxCount = 0;
	
    public static Props props(ActorRef out) {
        return Props.create(MyWebSocketActor.class, out);
    }

    private final ActorRef out;

    public MyWebSocketActor(ActorRef out) {
        this.out = out;
    }
    
    

    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            
        	String actualMessage = ((String) message).split(";")[0];
        	Integer count = 1 + Integer.parseInt(actualMessage);
			
        	out.tell("Just checking bitch " + count, self());
			
			maxCount = count;
			Logger.info(actualMessage);
			
        }
    }
}