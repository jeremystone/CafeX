package model

sealed trait MenuItem {
  def hot: Boolean

  def food: Boolean

  def price: Money
}

object MenuItem {

  case object Cola extends MenuItem {
    def hot: Boolean = false

    def food: Boolean = false

    def price: Money = Money(0.50)
  }

  case object Coffee extends MenuItem {
    def hot: Boolean = true

    def food: Boolean = false

    def price: Money = Money(1.00)
  }

  case object CheeseSandwich extends MenuItem {
    def hot: Boolean = false

    def food: Boolean = true

    def price: Money = Money(2.00)
  }

  case object SteakSandwich extends MenuItem {
    def hot: Boolean = true

    def food: Boolean = true

    def price: Money = Money(4.50)
  }

  // This would typically be a database lookup
  def parseByName(name: String): Option[MenuItem] =
    name match {
      case "Cola"            => Some(Cola)
      case "Coffee"          => Some(Coffee)
      case "Cheese Sandwich" => Some(CheeseSandwich)
      case "Steak Sandwich"  => Some(SteakSandwich)
      case _                 => None
    }
}
