using ch.hsr.wpf.gadgeothek.domain;
using ch.hsr.wpf.gadgeothek.service;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Configuration;
using System.Runtime.CompilerServices;

namespace Miniprojekt_WPF
{
    public class LoanListViewModel : INotifyPropertyChanged
    {
        private ObservableCollection<LoanViewModel> loanList;
        private LibraryAdminService libraryAdminService;

        public LoanListViewModel()
        {
            loanList = new ObservableCollection<LoanViewModel>();
            var serverAddress = ConfigurationManager.AppSettings["server"];
            libraryAdminService = new LibraryAdminService(serverAddress);
            libraryAdminService.GetAllLoans().Select(loan => new LoanViewModel(loan)).ToList().ForEach(loanList.Add);
        }

        public event PropertyChangedEventHandler PropertyChanged;

        public ObservableCollection<LoanViewModel> LoanList
        {
            get { return loanList; }
            set
            {
                loanList = value;
                OnPropertyChanged();
            }
        }

        private void OnPropertyChanged([CallerMemberName] string properyName = null)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(properyName));
        }
    }
}