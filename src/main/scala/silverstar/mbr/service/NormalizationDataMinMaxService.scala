package silverstar.mbr.service

import java.io.File

import purecsv.unsafe._
import silverstar.mbr.model.CentralHeatingPlantData

/**
  * Min-Max normalization data service.
  */
class NormalizationDataMinMaxService extends NormalizationDataService {

  override def createNormalizedData(inputFile: File, outputFile: Option[File] = None): File = {
    val csvExtension: String = ".csv"
    val defaultOutputPostfix: String = "_norm"

    val output: File = outputFile.getOrElse(new File(inputFile.getAbsolutePath.replace(csvExtension,
      defaultOutputPostfix + csvExtension)))
    val normalizationParameters: NormalizationParameters = calculateNormalizationParameters(inputFile)
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

  override def calculateNormalizationParameters(file: File): NormalizationParameters = {
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
