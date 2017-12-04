using System.Windows;

namespace Miniprojekt_WPF
{
    public partial class GadgetAddWindow : Window
    {
        private string inventoryNumber;

        public GadgetAddWindow()
        {
            InitializeComponent();
            DataContext = new GadgetAddViewModel();
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            Close();
        }
    }
}
