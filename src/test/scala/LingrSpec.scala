import org.scalatest.WordSpec
import org.scalatest.matchers.MustMatchers

import dispatch._

import com.github.hexx.dispatch.lingr._

class LingrSpec extends WordSpec with MustMatchers {
  val name = "dispatch-lingr-test"
  val apiKey = "8ilYP0"
  val secret = "JRvZKmqqll20gjM2PaTBor0Q4qK"
  val user = "pab"
  val password = "pablingr"

  "dispatch-lingr" can {
    "Session.create" in {
      val s = for {
        r1 <- Http(Session.create(apiKey, user, password))
        _  <- Http(Session.destroy(apiKey, r1.session))
      } yield r1.status
      s() must be === "ok"
    }
    "Session.verify" in {
      val s = for {
        r1 <- Http(Session.create(apiKey, user, password))
        r2 <- Http(Session.verify(apiKey, r1.session))
        _  <- Http(Session.destroy(apiKey, r1.session))
      } yield r2.status
      s() must be === "ok"
    }
    "User.getRooms" in {
      val s = for {
        r1 <- Http(Session.create(apiKey, user, password))
        r2 <- Http(User.getRooms(apiKey, r1.session))
        _  <- Http(Session.destroy(apiKey, r1.session))
      } yield r2.status
      s() must be === "ok"
    }
    "Room.show" in {
      val s = for {
        r1 <- Http(Session.create(apiKey, user, password))
        r2 <- Http(User.getRooms(apiKey, r1.session))
        r3 <- Http(Room.show(apiKey, r1.session, r2.rooms.head))
        _  <- Http(Session.destroy(apiKey, r1.session))
      } yield r3.status
      s() must be === "ok"
    }
    "Room.getArchives" in {
      val s = for {
        r1 <- Http(Session.create(apiKey, user, password))
        r2 <- Http(User.getRooms(apiKey, r1.session))
        r3 <- Http(Room.getArchives(apiKey, r1.session, r2.rooms.head))
        _  <- Http(Session.destroy(apiKey, r1.session))
      } yield r3.status
      s() must be === "ok"
    }
    "Room.say" in {
      val s = for {
        r1 <- Http(Session.create(apiKey, user, password))
        r2 <- Http(User.getRooms(apiKey, r1.session))
        r3 <- Http(Room.say(apiKey, r1.session, r2.rooms.head, "スーパーうんこうまいたるー"))
        _  <- Http(Session.destroy(apiKey, r1.session))
      } yield r3.status
      s() must be === "ok"
    }
    "Favorite.add" in {
      val s = for {
        r1 <- Http(Session.create(apiKey, user, password))
        r2 <- Http(User.getRooms(apiKey, r1.session))
        r3 <- Http(Room.show(apiKey, r1.session, r2.rooms.head))
        r4 <- Http(Favorite.add(apiKey, r1.session, r3.rooms.head.messages.head.id))
        _  <- Http(Session.destroy(apiKey, r1.session))
      } yield r4.status
      s() must be === "ok"
    }
    "Favorite.remove" in {
      val s = for {
        r1 <- Http(Session.create(apiKey, user, password))
        r2 <- Http(User.getRooms(apiKey, r1.session))
        r3 <- Http(Room.show(apiKey, r1.session, r2.rooms.head))
        r4 <- Http(Favorite.remove(apiKey, r1.session, r3.rooms.head.messages.head.id))
        _  <- Http(Session.destroy(apiKey, r1.session))
      } yield r4.status
      s() must be === "ok"
    }
  }
}
