package silverstar.mbr.statistics

import org.scalatest.{FunSpec, Matchers}
import silverstar.mbr.model.CentralHeatingPlantData

/**
  * Test for Infinity distance.
  */
class InfinitySpec extends FunSpec with Matchers {

  describe("Infinity distance for List[Double] should") {

    it("return None if list lengths do not match") {
      Infinity.distance(List(1.0, 1.0, 1.0), List(1.0, 0.2)) shouldBe None
    }

    it("return None for empty lists") {
      Infinity.distance(List(), List()) shouldBe None
    }

    it("return 0 for identical lists") {
      Infinity.distance(List(1.0, 1.0, 1.0), List(1.0, 1.0, 1.0)) shouldBe Some(0.0)
    }

    it("return result") {
      Infinity.distance(List(1.0, 1.0, 1.0, 1.0), List(1.0, 0.2, 1.0, 0.4)) shouldBe Some(0.8)
    }
  }

  describe("Infinity distance for CentralHeatingPlantData should") {

    it("return 0 for identical data") {
      val data1: CentralHeatingPlantData = CentralHeatingPlantData(1,1,1)
      val data2: CentralHeatingPlantData = data1.copy()
      Infinity.distance(data1, data2) shouldBe Some(0.0)
    }

    it("return result") {
      val data1: CentralHeatingPlantData = CentralHeatingPlantData(1,1,1)
      val data2: CentralHeatingPlantData = CentralHeatingPlantData(0.2,1,0.4)
      Infinity.distance(data1, data2) shouldBe Some(0.8)
    }
  }
}
