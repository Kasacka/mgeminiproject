using ch.hsr.wpf.gadgeothek.domain;
using System.ComponentModel;
using System.Runtime.CompilerServices;

namespace Miniprojekt_WPF.ViewModel
{
    public class GadgetViewModel : INotifyPropertyChanged
    {
        public GadgetViewModel(Gadget gadget)
        {
            Gadget = gadget;
        }

        public Gadget Gadget
        {
            get;
        }

        public string InventoryNumber
        {
            get { return Gadget.InventoryNumber; }
            set
            {
                Gadget.InventoryNumber = value;
                OnPropertyChanged();
            }
        }

        public string Condition
        {
            get { return Gadget.Condition.ToString(); }
        }

        public double Price
        {
            get { return Gadget.Price; }
            set
            {
                Gadget.Price = value;
                OnPropertyChanged();
            }
        }

        public string Manufacturer
        {
            get { return Gadget.Manufacturer; }
            set
            {
                Gadget.Manufacturer = value;
                OnPropertyChanged();
            }
        }
        
        public string Name
        {
            get { return Gadget.Name; }
            set
            {
                Gadget.Name = value;
                OnPropertyChanged();
            }
        }
        
        private void OnPropertyChanged([CallerMemberName] string properyName = null)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(properyName));
        }

        public event PropertyChangedEventHandler PropertyChanged;
    }
}