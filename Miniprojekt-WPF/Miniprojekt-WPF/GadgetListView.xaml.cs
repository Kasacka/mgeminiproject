using System.Windows.Controls;

namespace Miniprojekt_WPF
{
    public partial class GadgetListView : UserControl
    {
        public GadgetListView()
        {
            InitializeComponent();
            DataContext = new GadgetListViewModel();
        }
    }
}
