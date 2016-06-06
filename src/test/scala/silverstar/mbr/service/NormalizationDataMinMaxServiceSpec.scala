package silverstar.mbr.service

import java.io.File

import org.scalatest.{FunSpec, Matchers}
import silverstar.mbr.model.CentralHeatingPlantData

/**
  * Tests for NormalizationDataMinMaxService.
  */
class NormalizationDataMinMaxServiceSpec extends FunSpec with Matchers {

  val testFileName: String = "/tzas_tpow_tzew_small.csv"
  val testFileNameNorm: String = "/tzas_tpow_tzew_small_norm.csv"

  def normalizationDataMinMaxService: NormalizationDataMinMaxService = {
    new NormalizationDataMinMaxService()
  }

  describe("NormalizationDataMinMaxService getNormalizationParameters should") {

    it("return normalization parameters for proper csv file") {

      val inputFile: File = new File(getClass.getResource(testFileName).getPath)
      val normalizationParameters: NormalizationParameters =
        normalizationDataMinMaxService.calculateNormalizationParameters(inputFile)

      normalizationParameters.minValueDataPoint shouldBe CentralHeatingPlantData(72.5, 46.9, -9.4)
      normalizationParameters.maxValueDataPoint shouldBe CentralHeatingPlantData(101.8, 58.4, 2.1)
    }
  }

  describe("NormalizationDataMinMaxService createNormalizedData should") {

    it("return csv file with normalized data") {
      val inputFile: File = new File(getClass.getResource(testFileName).getPath)
      val outputFile: File = normalizationDataMinMaxService.createNormalizedData(inputFile)

      val normalizationParameters: NormalizationParameters =
        normalizationDataMinMaxService.calculateNormalizationParameters(outputFile)
      val expectedOutputFile: File = new File(getClass.getResource(testFileNameNorm).getPath)

      outputFile.getAbsoluteFile shouldBe expectedOutputFile
      normalizationParameters.minValueDataPoint shouldBe CentralHeatingPlantData(0.0, 0.0, 0.0)
      normalizationParameters.maxValueDataPoint shouldBe CentralHeatingPlantData(1.0, 1.0, 1.0)
    }
  }
}
