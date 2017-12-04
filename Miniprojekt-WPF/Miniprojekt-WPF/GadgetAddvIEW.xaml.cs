using System.Windows;
using System.Windows.Controls;

namespace Miniprojekt_WPF
{
    public partial class GadgetAddView : UserControl
    {
        public GadgetAddView()
        {
            InitializeComponent();
            DataContext = new GadgetAddViewModel(GetNavigationContext());
        }

        private INavigationContext GetNavigationContext()
        {
            return (INavigationContext) Application.Current.MainWindow;
        }
    }
}