using ch.hsr.wpf.gadgeothek.domain;
using ch.hsr.wpf.gadgeothek.service;
using System.ComponentModel;
using System.Configuration;
using System.Runtime.CompilerServices;
using System.Windows.Input;

namespace Miniprojekt_WPF
{
    public sealed class GadgetAddViewModel : INotifyPropertyChanged
    {
        private readonly LibraryAdminService libraryAdminService;
        private readonly Gadget gadget;
        
        public GadgetAddViewModel()
        {
            var serverAddress = ConfigurationManager.AppSettings["server"];
            libraryAdminService = new LibraryAdminService(serverAddress);
            gadget = new Gadget();
            SaveCommand = new DelegateCommand(OnSaveGadget);
        }

        public string InventoryNumber
        {
            get { return gadget.InventoryNumber; }
            set
            {
                gadget.InventoryNumber = value;
                OnPropertyChanged();
            }
        }

        public string Manufacturer
        {
            get { return gadget.Manufacturer; }
            set
            {
                gadget.Manufacturer = value;
                OnPropertyChanged();
            }
        }

        public string Name
        {
            get { return gadget.Name; }
            set
            {
                gadget.Name = value;
                OnPropertyChanged();
            }
        }

        public double Price
        {
            get { return gadget.Price; }
            set
            {
                gadget.Price = value;
                OnPropertyChanged();
            }
        }

        public ICommand SaveCommand
        {
            get; private set;
        }
        
        private void OnSaveGadget()
        {
            libraryAdminService.AddGadget(gadget);
        }

        public event PropertyChangedEventHandler PropertyChanged;

        private void OnPropertyChanged([CallerMemberName] string properyName = null)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(properyName));
        }
    }
}
