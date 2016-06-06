package silverstar.mbr.service

import java.io.File

import silverstar.mbr.model.DataPoint

/**
  * Normalization service.
  */
trait NormalizationDataService {

  def getNormalizationParameters(file: File): NormalizationParameters

  def createNormalizedData(inputFile: File, outputFile: Option[File]): File
}

case class NormalizationParameters(minValueDataPoint: DataPoint, maxValueDataPoint: DataPoint)
