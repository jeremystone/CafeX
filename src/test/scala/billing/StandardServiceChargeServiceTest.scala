package billing

import model.MenuItem.{CheeseSandwich, Coffee, Cola, SteakSandwich}
import model.Money
import org.scalatest.{MustMatchers, WordSpec}

/**
  * Tests [[StandardServiceChargeService]]
  */
class StandardServiceChargeServiceTest extends WordSpec with MustMatchers {

  val serviceChargeService = new StandardServiceChargeService

  "StandardServiceCharge" when {
    "all items are drinks" must {
      "apply no service charge" in {
        serviceChargeService.calculateServiceCharge(
          Money(1.0),
          List(Cola, Coffee)) mustBe Money.Zero
      }
    }

    "any items include food (but not hot food)" must {
      "apply 10% service charge" in {
        serviceChargeService.calculateServiceCharge(
          Money(1.0),
          List(Cola, Coffee, CheeseSandwich)) mustBe Money(0.10)
      }

      "apply 10% service charge even if multiple cold food items" in {
        serviceChargeService.calculateServiceCharge(
          Money(1.0),
          List(Cola, Coffee, CheeseSandwich, CheeseSandwich)) mustBe Money(0.10)
      }
    }

    "any items include hot food" must {
      "apply 20% service charge" in {
        serviceChargeService.calculateServiceCharge(
          Money(1.0),
          List(Cola, Coffee, SteakSandwich)) mustBe Money(0.20)
      }

      "apply 20% service charge even if multiple food items" in {
        serviceChargeService.calculateServiceCharge(
          Money(1.0),
          List(Cola, Coffee, CheeseSandwich, SteakSandwich)) mustBe Money(0.20)
      }
    }

    // TODO CHECK WITH CLIENT as restrictions are unclear as to whether the £20 limit applies only to hot food.
    // Suspect it applies to all service charges...
    "restrict service charge to £20" in {
      serviceChargeService.calculateServiceCharge(
        Money(1000.0),
        List(Cola, Coffee, CheeseSandwich, SteakSandwich)) mustBe Money(20.0)
    }

    "rounds the charge to the nearest penny" in {
      serviceChargeService.calculateServiceCharge(
        Money(0.09),
        List(CheeseSandwich)) mustBe Money(0.01)
    }
  }
}
