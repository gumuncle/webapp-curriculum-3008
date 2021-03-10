import akka.actor.{Actor, ActorSystem, Props}

class MyActor extends Actor {
  var count = 0

  def receive = {
    case _ => {
      count = count + 1
      println(count)
    }
  }
}

object MessageCountActorApp extends App {
  val system = ActorSystem("actorStudy")

  val myActor = system.actorOf(Props[MyActor], "myActor")

  for (i <- 1 to 10000) {
    myActor ! "Hello!"
  }

  Thread.currentThread().join()
}
