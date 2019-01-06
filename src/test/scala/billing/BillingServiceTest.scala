package billing

import model.Money
import org.scalatest.{MustMatchers, WordSpec}

/**
  * Tests for [[BillingService]]
  */
class BillingServiceTest extends WordSpec with MustMatchers {
  "BillingService" when {

    val billingService = new BillingService(new MenuService)

    "no items are supplied" must {
      "calculate the cost as zero" in {
        billingService.calculateBillTotalFromNames(Nil) mustBe Money.Zero
      }
    }

    "given an individual item name" must {
      "total to the item's correct price" in {
        billingService.calculateBillTotalFromNames(List("Cola")) mustBe Money(0.5)
        billingService.calculateBillTotalFromNames(List("Coffee")) mustBe Money(1.0)
        billingService.calculateBillTotalFromNames(List("Cheese Sandwich")) mustBe Money(2.0)
        billingService.calculateBillTotalFromNames(List("Steak Sandwich")) mustBe Money(4.50)
      }
    }

    "given a list of supplied item names" must {
      "calculate the total amount" in {
        billingService.calculateBillTotalFromNames(List("Cola", "Coffee", "Cheese Sandwich")) mustBe Money(3.5)
      }
    }
  }
}
