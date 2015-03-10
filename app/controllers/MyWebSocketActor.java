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
            
        	Integer count = 1 + Integer.parseInt(((String)message).substring(0, 1));
			
        	out.tell("Just checking bitch " + count, self());
			if (count > maxCount) {
				maxCount = count;
				Logger.info(((String)message).substring(0,1));
			}
        }
    }
}