using Miniprojekt_WPF.ViewModel;
using System;
using System.Windows;
using System.Windows.Controls;

namespace Miniprojekt_WPF
{
    public partial class GadgetAddView : UserControl
    {
        private readonly GadgetAddViewModel gadgetAddViewModel;
        
        public GadgetAddView(GadgetViewModel gadget, Action onGadgetListChanged)
        {
            InitializeComponent();
            gadgetAddViewModel = new GadgetAddViewModel(GetNavigationContext(), gadget);
            gadgetAddViewModel.OnGadgetListChanged += onGadgetListChanged;
            DataContext = gadgetAddViewModel;
        }
       
        private INavigationContext GetNavigationContext()
        {
            return (INavigationContext) Application.Current.MainWindow;
        }
    }
}