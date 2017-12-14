using System;
using System.Windows;

namespace Miniprojekt_WPF
{
    public partial class GadgetDeleteWindow : Window
    {
        public GadgetDeleteWindow()
        {
            InitializeComponent();
        }

        public void YesClicked(object sender, EventArgs e)
        {
            CloseWindowWithResult(true);
        }

        public void NoClicked(object sender, EventArgs e)
        {
            CloseWindowWithResult(false);
        }

        private void CloseWindowWithResult(bool dialogResult)
        {
            DialogResult = dialogResult;
        }
    }
}
