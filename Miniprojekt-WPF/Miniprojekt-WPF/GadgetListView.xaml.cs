using System.Windows;
using System.Windows.Controls;

namespace Miniprojekt_WPF
{
    public partial class GadgetListView : UserControl
    {
        public GadgetListView()
        {
            InitializeComponent();
            DataContext = new GadgetListViewModel(GetNavigationContext());
        }

        private INavigationContext GetNavigationContext()
        {
            return (INavigationContext)Application.Current.MainWindow;
        }
    }
}