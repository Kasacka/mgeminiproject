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
        private ObservableCollection<Loan> loanList;
        private LibraryAdminService libraryAdminService;

        public LoanListViewModel()
        {
            loanList = new ObservableCollection<Loan>();
            var serverAddress = ConfigurationManager.AppSettings["server"];
            libraryAdminService = new LibraryAdminService(serverAddress);
            libraryAdminService.GetAllLoans().ForEach(loanList.Add);
        }

        public event PropertyChangedEventHandler PropertyChanged;

        public ObservableCollection<Loan> LoanList
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