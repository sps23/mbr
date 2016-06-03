package silverstar.mbr.statistics

import org.scalatest.{FunSpec, Matchers}
import silverstar.mbr.model.CentralHeatingPlantData

/**
  * Test for distance.
  */
class EuclideanSpec extends FunSpec with Matchers {

  describe("Euclidean distance for List[Double] should") {

    it("return None if list lengths do not match") {
      Euclidean.distance(List(1.0, 1.0, 1.0), List(1.0, 0.2)) shouldBe None
    }

    it("return None for empty lists") {
      Euclidean.distance(List(), List()) shouldBe None
    }

    it("return 0 for identical lists") {
      Euclidean.distance(List(1.0, 1.0, 1.0), List(1.0, 1.0, 1.0)) shouldBe Some(0.0)
    }

    it("return result") {
      Euclidean.distance(List(1.0, 1.0, 1.0, 1.0), List(1.0, 0.2, 1.0, 0.4)) shouldBe Some(1.0 / 4.0)
    }
  }

  describe("Euclidean distance for CentralHeatingPlantData should") {

    it("return 0 for identical data") {
      val data1: CentralHeatingPlantData = CentralHeatingPlantData(1,1,1)
      val data2: CentralHeatingPlantData = data1.copy()
      Euclidean.distance(data1, data2) shouldBe Some(0.0)
    }

    it("return result") {
      val data1: CentralHeatingPlantData = CentralHeatingPlantData(1,1,1)
      val data2: CentralHeatingPlantData = CentralHeatingPlantData(0.2,1,0.4)
      Euclidean.distance(data1, data2) shouldBe Some(1.0 / 3.0)
    }
  }
}
