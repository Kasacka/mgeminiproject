using ch.hsr.wpf.gadgeothek.service;
using Miniprojekt_WPF.ViewModel;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Configuration;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Windows.Input;

namespace Miniprojekt_WPF
{
    public class GadgetListViewModel : INotifyPropertyChanged
    {
        private readonly INavigationContext navigationContext;
        private ObservableCollection<GadgetViewModel> gadgetList;
        private GadgetViewModel selectedGadget;
        private LibraryAdminService libraryAdminService;
        
        public GadgetListViewModel(INavigationContext navigationContext)
        {
            gadgetList = new ObservableCollection<GadgetViewModel>();
            var serverAddress = ConfigurationManager.AppSettings["server"];
            libraryAdminService = new LibraryAdminService(serverAddress);
            libraryAdminService.GetAllGadgets()?
                .Select(gadget => new GadgetViewModel(gadget))
                .ToList().ForEach(gadgetList.Add);

            GadgetDeleteCommand = new DelegateCommand(OnDeleteGadget, CanEditOrDelete);
            GadgetAddCommand = new DelegateCommand(OnAddGadget);
            GadgetEditCommand = new DelegateCommand(OnEditGadget, CanEditOrDelete);

            this.navigationContext = navigationContext;
        }

        private bool CanEditOrDelete()
        {
            return SelectedGadget != null;
        }

        public event PropertyChangedEventHandler PropertyChanged;
        
        public ObservableCollection<GadgetViewModel> GadgetList
        {
            get { return gadgetList; }
            set
            {
                gadgetList = value;
                OnPropertyChanged();
            }
        }

        public GadgetViewModel SelectedGadget
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
                libraryAdminService.DeleteGadget(SelectedGadget.Gadget);
                gadgetList.Remove(SelectedGadget);
            }
        }

        private void OnAddGadget()
        {
            navigationContext.StartView(new GadgetAddView(null, OnGadgetListChanged));
        }

        private void OnEditGadget()
        {
            if (SelectedGadget != null)
            {
                navigationContext.StartView(new GadgetAddView(SelectedGadget, OnGadgetListChanged));
            }
        }

        private void OnGadgetListChanged()
        {
            gadgetList.Clear();
            libraryAdminService.GetAllGadgets()
                .Select(gadget => new GadgetViewModel(gadget))
                .ToList()
                .ForEach(gadgetList.Add);
        }

        private void OnPropertyChanged([CallerMemberName] string properyName = null)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(properyName));
        }
    }
}
