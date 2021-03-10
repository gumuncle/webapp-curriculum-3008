import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging

case object HelloJapanese
case object Hello

class HelloJapaneseActor extends Actor {
  val log = Logging(context.system, this)
  def receive = {
    case HelloJapanese => {
      log.info("hello japanese")
      sender() ! Hello
    }
    case _ =>
  }
}

class HelloActor extends Actor {
  val log = Logging(context.system, this)
  def receive = {
    case Hello => {
      log.info("hello")
      sender() ! HelloJapanese
    }
    case _ =>
  }
}
object ReplyActorApp extends App {

  val system = ActorSystem("ReplyActor")

  val helloJapaneseActor =
    system.actorOf(Props[HelloJapaneseActor], "helloJapaneseActor")
  val helloActor = system.actorOf(Props[HelloActor], "helloActor")

  helloJapaneseActor.tell(HelloJapanese, helloActor)
}
