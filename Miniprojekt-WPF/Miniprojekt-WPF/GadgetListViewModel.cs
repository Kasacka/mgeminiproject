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
        private ObservableCollection<Gadget> gadgetList;
        private LibraryAdminService libraryAdminService;
        private string abc = "jdfhjkh";

        public GadgetListViewModel()
        {
            gadgetList = new ObservableCollection<Gadget>();
            var serverAddress = ConfigurationManager.AppSettings["server"];
            libraryAdminService = new LibraryAdminService(serverAddress);
            libraryAdminService.GetAllGadgets().ForEach(gadgetList.Add);
            GadgetDeleteCommand = new DelegateCommand(OnDeleteGadget);
        }

        public event PropertyChangedEventHandler PropertyChanged;

        public string Abc {
            get { return abc; }
            set
            {
                abc = value;
                OnPropertyChanged();
            }
        }

        public ObservableCollection<Gadget> GadgetList
        {
            get { return gadgetList; }
            set
            {
                gadgetList = value;
                OnPropertyChanged();
            }
        }

        public ICommand GadgetDeleteCommand
        {
            get; private set;
        }

        private void OnDeleteGadget()
        {
            Abc = "Test Test";
        }

        private void OnPropertyChanged([CallerMemberName] string properyName = null)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(properyName));
        }
    }
}
