using ch.hsr.wpf.gadgeothek.domain;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Miniprojekt_WPF.ViewModel;
using System.Text;

namespace Miniprojekt_WPFTesting
{
    [TestClass]
    public sealed class GadgetViewModelTest
    {
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

        [TestMethod]
        public void TestGadgetViewModelConstruct()
        {
            var gadget = CreateSampleGadget();
            var gadgetViewModel = new GadgetViewModel(gadget);

            Assert.AreEqual("Lost", gadgetViewModel.Condition);
            Assert.AreEqual("12", gadgetViewModel.InventoryNumber);
            Assert.AreEqual(15d, gadgetViewModel.Price);
            Assert.AreEqual("IBM", gadgetViewModel.Manufacturer);
            Assert.AreEqual("Fisch", gadgetViewModel.Name);
        }

        [TestMethod]
        public void TestGadgetPropertyChangedNotification()
        {
            var gadget = CreateSampleGadget();
            var gadgetViewModel = new GadgetViewModel(gadget);
            var output = new StringBuilder();

            gadgetViewModel.PropertyChanged += (sender, eventArgs) =>
            {
                output.Append(eventArgs.PropertyName);
            };

            gadgetViewModel.Name = "Test";
            gadgetViewModel.Manufacturer = "Test";

            Assert.AreEqual("NameManufacturer", output.ToString());
        }
    }
}
