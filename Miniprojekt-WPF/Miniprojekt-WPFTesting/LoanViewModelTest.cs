using ch.hsr.wpf.gadgeothek.domain;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;

namespace Miniprojekt_WPFTesting
{
    [TestClass]
    public sealed class LoanViewModelTest
    {
        private static Customer CreateSampleCustomer()
        {
            return new Customer
            {
                Email = "test@test.ch",
                Name = "Test",
                Password = "test",
                Studentnumber = "9438"
            };
        }

        private static Gadget CreateSampleGadget()
        {
            return new Gadget
            {
                Condition = Condition.Lost,
                InventoryNumber = "12",
                Name = "Fisch",
                Price = 15d,
                Manufacturer = "IBM"
            };
        }

        private static Loan CreateSampleLoan()
        {
            return new Loan
            {
                Customer = CreateSampleCustomer(),
                CustomerId = "123",
                Gadget = CreateSampleGadget(),
                GadgetId = "123",
                Id = "12",
                PickupDate = new DateTime(2017, 1, 1),
                ReturnDate = null
            };
        }

        [TestMethod]
        public void TestLoanViewModelConstruct()
        {
            var loan = CreateSampleLoan();
            var loanViewModel = new LoanViewModel(loan);

            Assert.AreEqual("Lost", loanViewModel.Condition);
            Assert.AreEqual("12", loanViewModel.InventoryNumber);
            Assert.AreEqual(15d, loanViewModel.Price);
            Assert.AreEqual("IBM", loanViewModel.Manufacturer);
            Assert.AreEqual("Fisch", loanViewModel.Name);
        }

        [TestMethod]
        public void TestLoanPropertyChangedNotification()
        {
            var loan = CreateSampleLoan();
            var loanViewModel = new LoanViewModel(loan);
            var output = new StringBuilder();

            loanViewModel.PropertyChanged += (sender, eventArgs) =>
            {
                output.Append(eventArgs.PropertyName);
            };

            loanViewModel.Name = "Test";
            loanViewModel.Manufacturer = "Test";

            Assert.AreEqual("NameManufacturer", output.ToString());
        }
    }
}
