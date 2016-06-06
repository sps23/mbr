package silverstar.mbr.statistics

import silverstar.mbr.model.DataPoint

case class Infinity() extends Distance {

  override def distance(x: DataPoint, y: DataPoint): Option[Double] =
    distance(x.toList(), y.toList())

  override def distance(x: List[Double], y: List[Double]): Option[Double] = {
    if (x.length == y.length) {
      if (x.isEmpty) {
        None
      } else {
        val diffAbs: List[Double] = (x, y).zipped.map((xx, yy) => Math.abs(xx - yy))
        val maxOfDiffAbs: Double = diffAbs.max
        Some(maxOfDiffAbs)
      }
    } else {
      None
    }
  }
}

object Infinity extends Infinity