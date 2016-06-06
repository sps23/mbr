package silverstar.mbr.service

import java.io.File

import purecsv.unsafe._
import silverstar.mbr.model.CentralHeatingPlantData

/**
  * Min-Max normalization data service.
  */
class NormalizationDataMinMaxService extends NormalizationDataService {

  val CSVExtension: String = ".csv"
  val DefaultOutputPostfix: String = "_norm"

  override def createNormalizedData(inputFile: File, outputFile: Option[File] = None): File = {
    val output: File = outputFile.getOrElse(new File(inputFile.getAbsolutePath.replace(CSVExtension,
      DefaultOutputPostfix + CSVExtension)))
    val normalizationParameters: NormalizationParameters = getNormalizationParameters(inputFile)
    val min: CentralHeatingPlantData = normalizationParameters.minValueDataPoint.asInstanceOf[CentralHeatingPlantData]
    val max: CentralHeatingPlantData = normalizationParameters.maxValueDataPoint.asInstanceOf[CentralHeatingPlantData]
    val maxMinusMin: CentralHeatingPlantData = max - min

    val reader: CSVReader[CentralHeatingPlantData] = CSVReader[CentralHeatingPlantData]
    val centralHeatingPlantDataList: List[CentralHeatingPlantData] = reader.readCSVFromFile(f = inputFile, skipHeader = true)

    val centralHeatingPlantDataNormalized: List[CentralHeatingPlantData] = centralHeatingPlantDataList.map(d => (d - min) / maxMinusMin)
    val header: Seq[String] = Seq("tZas", "tPow", "tZew")
    centralHeatingPlantDataNormalized.writeCSVToFile(file = output, header = Some(header))
    output
  }

  override def getNormalizationParameters(file: File): NormalizationParameters = {
    val reader: CSVReader[CentralHeatingPlantData] = CSVReader[CentralHeatingPlantData]
    val centralHeatingPlantDataList: List[CentralHeatingPlantData] = reader.readCSVFromFile(f = file, skipHeader = true)

    val tZasMin: Double = centralHeatingPlantDataList.map(_.tZas).min
    val tZasMax: Double = centralHeatingPlantDataList.map(_.tZas).max
    val tPowMin: Double = centralHeatingPlantDataList.map(_.tPow).min
    val tPowMax: Double = centralHeatingPlantDataList.map(_.tPow).max
    val tZewMin: Double = centralHeatingPlantDataList.map(_.tZew).min
    val tZewMax: Double = centralHeatingPlantDataList.map(_.tZew).max

    val minValueDataPoint: CentralHeatingPlantData = CentralHeatingPlantData(tZas = tZasMin, tPow = tPowMin, tZew = tZewMin)
    val maxValueDataPoint: CentralHeatingPlantData = CentralHeatingPlantData(tZas = tZasMax, tPow = tPowMax, tZew = tZewMax)
    NormalizationParameters(minValueDataPoint, maxValueDataPoint)
  }
}
