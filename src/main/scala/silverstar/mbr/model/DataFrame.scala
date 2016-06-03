package silverstar.mbr.model

/**
  * Created by sylwe on 04.06.2016.
  */
trait DataFrame {

  def window: List[DataPoint]
  def prediction: List[DataPoint]

  def windowSize: Int = window.length
  def predictionSize: Int = prediction.length
}
