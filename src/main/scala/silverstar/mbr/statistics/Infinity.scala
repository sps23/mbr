package silverstar.mbr.statistics

import silverstar.mbr.model.DataPoint

case class Infinity() extends Distance {

  override def distance(x: List[Double], y: List[Double]): Option[Double] = {
    if (x.length == y.length) {
      if (x.isEmpty) None
      else Some((x, y).zipped.map(_ - _).max)
    }
    else None
  }

  override def distance(x: DataPoint, y: DataPoint): Option[Double] =
    distance(x.toList(), y.toList())
}

object Infinity extends Infinity