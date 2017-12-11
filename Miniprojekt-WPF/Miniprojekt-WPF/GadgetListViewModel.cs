using ch.hsr.wpf.gadgeothek.domain;
using ch.hsr.wpf.gadgeothek.service;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Configuration;
using System.Runtime.CompilerServices;
using System.Windows.Input;

namespace Miniprojekt_WPF
{
    public class GadgetListViewModel : INotifyPropertyChanged
    {
        private readonly INavigationContext navigationContext;
        private ObservableCollection<Gadget> gadgetList;
        private Gadget selectedGadget;
        private LibraryAdminService libraryAdminService;
        
        public GadgetListViewModel(INavigationContext navigationContext)
        {
            gadgetList = new ObservableCollection<Gadget>();
            var serverAddress = ConfigurationManager.AppSettings["server"];
            libraryAdminService = new LibraryAdminService(serverAddress);
            libraryAdminService.GetAllGadgets().ForEach(gadgetList.Add);

            GadgetDeleteCommand = new DelegateCommand(OnDeleteGadget);
            GadgetAddCommand = new DelegateCommand(OnAddGadget);
            GadgetEditCommand = new DelegateCommand(OnEditGadget);

            this.navigationContext = navigationContext;
        }

        public event PropertyChangedEventHandler PropertyChanged;
        
        public ObservableCollection<Gadget> GadgetList
        {
            get { return gadgetList; }
            set
            {
                gadgetList = value;
                OnPropertyChanged();
            }
        }

        public Gadget SelectedGadget
        {
            get { return selectedGadget; }
            set
            {
                selectedGadget = value;
                OnPropertyChanged();
            }
        }

        public ICommand GadgetDeleteCommand
        {
            get; private set;
        }

        public ICommand GadgetAddCommand
        {
            get; private set;
        }

        public ICommand GadgetEditCommand
        {
            get; private set;
        }

        private void OnDeleteGadget()
        {
            if (SelectedGadget == null)
                return;
            
            var window = new GadgetDeleteWindow();

            if (window.ShowDialog().Value)
            {
                libraryAdminService.DeleteGadget(SelectedGadget);
                gadgetList.Remove(SelectedGadget);
            }
        }

        private void OnAddGadget()
        {
            navigationContext.StartView(new GadgetAddView(null, OnGadgetListChanged));
        }

        private void OnEditGadget()
        {
            if (SelectedGadget == null)
                return;

            navigationContext.StartView(new GadgetAddView(SelectedGadget, OnGadgetListChanged));
        }

        private void OnGadgetListChanged()
        {
            gadgetList.Clear();
            libraryAdminService.GetAllGadgets().ForEach(gadgetList.Add);
        }

        private void OnPropertyChanged([CallerMemberName] string properyName = null)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(properyName));
        }
    }
}
