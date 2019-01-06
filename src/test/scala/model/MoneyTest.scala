package model

import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{MustMatchers, WordSpec}


/**
  * Tests for [[Money]]
  */
class MoneyTest extends WordSpec
  with GeneratorDrivenPropertyChecks
  with MustMatchers {

  "Money" when {
    "added to other Money" must {
      "add the underlying amount" in {
        forAll { (amount1: Double, amount2: Double) =>
          Money(amount1) + Money(amount2) mustBe Money(amount1 + amount2)
        }
      }
    }

    "subtracted from other Money" must {
      "subtract the underlying amount" in {
        forAll { (amount1: Double, amount2: Double) =>
          Money(amount1) - Money(amount2) mustBe Money(amount1 - amount2)
        }
      }
    }

    "multiplied by an integer" must {
      "multiply the underlying amount" in {
        forAll { (amount: Double, multiple: Double) =>
          Money(amount) * multiple  mustBe Money(amount * multiple)
        }
      }
    }

    "rounding to nearest penny" must {
      "round up" in {
        Money(12.345).roundToNearestPenny mustBe Money(12.35)
      }
      "round down" in {
        Money(12.344).roundToNearestPenny mustBe Money(12.34)
      }
    }
  }
}
