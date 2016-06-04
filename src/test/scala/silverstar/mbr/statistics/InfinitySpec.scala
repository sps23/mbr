package silverstar.mbr.statistics

import org.scalatest.{FunSpec, Matchers}
import silverstar.mbr.statistics.DistanceTestData._

/**
  * Test for Infinity distance.
  */
class InfinitySpec extends FunSpec with Matchers {

  describe("Infinity distance for List[Double] should") {

    it("return None if list lengths do not match") {
      Infinity.distance(data1, dataOnly2Points) shouldBe None
    }

    it("return None for empty lists") {
      Infinity.distance(List(), List()) shouldBe None
    }

    it("return 0 for identical lists (identity check)") {
      Infinity.distance(data1, data1) shouldBe Some(0.0)
    }

    it("return result") {
      Infinity.distance(data2, data3) shouldBe Some(0.8)
    }
  }

  describe("Infinity distance for CentralHeatingPlantData should") {

    it("return 0 for identical data (identity check)") {
      Infinity.distance(chpData1, chpData1) shouldBe Some(0.0)
    }

    it("return same result if we swap data (commutativity check)") {
      Infinity.distance(chpData1, chpData2) shouldBe Infinity.distance(chpData2, chpData1)
    }

    it("return result conforming triangular inequality") {
      val distanceAB: Double = Infinity.distance(chpData1, chpData2).getOrElse(0.0)
      val distanceACPlusCB: Double = Infinity.distance(chpData1, chpData3).getOrElse(0.0) +
        Infinity.distance(chpData3, chpData2).getOrElse(0.0)
      distanceAB <= distanceACPlusCB shouldBe true
    }

    it("return result") {
      Infinity.distance(chpData1, chpData2) shouldBe Some(0.8)
    }
  }
}
