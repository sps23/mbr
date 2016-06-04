package silverstar.mbr.statistics

import org.scalatest.{FunSpec, Matchers}
import silverstar.mbr.statistics.DistanceTestData._

/**
  * Test for distance.
  */
class EuclideanSpec extends FunSpec with Matchers {

  describe("Euclidean distance for List[Double] should") {

    it("return None if list lengths do not match") {
      Euclidean.distance(data1, dataOnly2Points) shouldBe None
    }

    it("return None for empty lists") {
      Euclidean.distance(List(), List()) shouldBe None
    }

    it("return 0 for identical lists (identity check)") {
      Euclidean.distance(data1, data1) shouldBe Some(0.0)
    }

    it("return result") {
      Euclidean.distance(data2, data3) shouldBe Some(1.0 / 4.0)
    }
  }

  describe("Euclidean distance for CentralHeatingPlantData should") {

    it("return 0 for identical data (identity check)") {
      Euclidean.distance(chpData1, chpData1) shouldBe Some(0.0)
    }

    it("return same result if we swap data (commutativity check)") {
      Euclidean.distance(chpData1, chpData2) shouldBe Euclidean.distance(chpData2, chpData1)
    }

    it("return result conforming triangular inequality") {
      val distanceAB: Double = Euclidean.distance(chpData1, chpData2).getOrElse(0.0)
      val distanceACPlusCB: Double = Euclidean.distance(chpData1, chpData2).getOrElse(0.0) +
        Euclidean.distance(chpData1, chpData2).getOrElse(0.0)
      distanceAB <= distanceACPlusCB shouldBe true
    }

    it("return result") {
      Euclidean.distance(chpData1, chpData2) shouldBe Some(1.0 / 3.0)
    }
  }
}
