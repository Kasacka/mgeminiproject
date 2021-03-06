﻿using ch.hsr.wpf.gadgeothek.domain;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Miniprojekt_WPF.ViewModel;
using System;
using System.Text;

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

            Assert.AreEqual("Test", loanViewModel.CustomerName);
            Assert.AreEqual("Fisch", loanViewModel.GadgetName);
            Assert.AreEqual("01.01.2017", loanViewModel.PickupDate);
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

            loanViewModel.CustomerName = "Abc";
            loanViewModel.GadgetName = "Test";

            Assert.AreEqual("CustomerNameGadgetName", output.ToString());
        }
    }
}
