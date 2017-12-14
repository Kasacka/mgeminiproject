using ch.hsr.wpf.gadgeothek.domain;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;

namespace Miniprojekt_WPF.ViewModel
{
    public class LoanViewModel : INotifyPropertyChanged
    {
        private Loan Loan { get; set; }

        public event PropertyChangedEventHandler PropertyChanged;

        public LoanViewModel (Loan loan)
        {
            Loan = loan;
        }

        public string CustomerName
        {
            get { return Loan.Customer?.Name; }
            set
            {
                Loan.Customer.Name = value;
                OnPropertyChanged();
            }
        }

        public string GadgetName
        {
            get { return Loan.Gadget?.Name; }
            set
            {
                Loan.Gadget.Name = value;
                OnPropertyChanged();
            }
        }

        public string PickupDate
        {
            get { return Loan.PickupDate.Value.ToString("dd.MM.yyyy"); }
            set
            {
                Loan.PickupDate = DateTime.ParseExact(value, "dd.MM.yyyy", null);
                OnPropertyChanged();
            }
        }

        public override string ToString()
        {
            return $"Loan {CustomerName} {GadgetName} from {PickupDate:yyyy-MM-dd}";
        }

        private void OnPropertyChanged([CallerMemberName] string properyName = null)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(properyName));
        }
    }
}
