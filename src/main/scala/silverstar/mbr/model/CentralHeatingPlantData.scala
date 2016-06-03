package silverstar.mbr.model

/**
  * Central heating plant data.
  */
case class CentralHeatingPlantData(tZas: Double,
                                   tPow: Double,
                                   tZew: Double) extends DataPoint {

  def toList(): List[Double] = List(tZas, tPow, tZew)

  def -(data: CentralHeatingPlantData): CentralHeatingPlantData = {
    new CentralHeatingPlantData(tZas = tZas - data.tZas, tPow = tPow - data.tPow, tZew = tZew - data.tZew)
  }
}
