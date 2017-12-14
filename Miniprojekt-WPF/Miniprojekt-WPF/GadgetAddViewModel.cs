using ch.hsr.wpf.gadgeothek.domain;
using ch.hsr.wpf.gadgeothek.service;
using System;
using System.ComponentModel;
using System.Configuration;
using System.Runtime.CompilerServices;
using System.Windows.Input;

namespace Miniprojekt_WPF
{
    public sealed class GadgetAddViewModel : INotifyPropertyChanged
    {
        private readonly INavigationContext navigationContext;
        private readonly LibraryAdminService libraryAdminService;
        private Gadget gadget;

        public event Action OnGadgetListChanged;
        
        public GadgetAddViewModel(INavigationContext navigationContext, Gadget gadget)
        {
            var serverAddress = ConfigurationManager.AppSettings["server"];
            libraryAdminService = new LibraryAdminService(serverAddress);

            if (gadget == null)
            {
                this.gadget = new Gadget();
            }
            else
            {
                this.gadget = gadget;
            }

            SaveCommand = new DelegateCommand(OnSaveGadget);
            CancelCommand = new DelegateCommand(OnCancel);

            this.navigationContext = navigationContext;
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

        public ICommand CancelCommand
        {
            get; private set;
        }

        public void LoadGadget(string inventoryNumber)
        {
            gadget = libraryAdminService.GetGadget(inventoryNumber);
        }
        
        private bool GadgetExists(Gadget gadget)
        {
            return libraryAdminService.GetGadget(gadget.InventoryNumber) != null;
        }

        private void OnSaveGadget()
        {
            if (GadgetExists(gadget))
            {
                libraryAdminService.UpdateGadget(gadget);
            }
            else
            {
                libraryAdminService.AddGadget(gadget);
            }

            navigationContext.CloseView();
            OnGadgetListChanged?.Invoke();
        }

        private void OnCancel()
        {
            navigationContext.CloseView();
        }

        public event PropertyChangedEventHandler PropertyChanged;

        private void OnPropertyChanged([CallerMemberName] string properyName = null)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(properyName));
        }
    }
}
