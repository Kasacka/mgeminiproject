using System.Collections.Generic;
using System.Windows;
using System.Windows.Controls;

namespace Miniprojekt_WPF
{
    public partial class MainWindow : Window, INavigationContext
    {
        private readonly Stack<UserControl> viewStack;

        public MainWindow()
        {
            InitializeComponent();
            viewStack = new Stack<UserControl>();
            StartView(new GadgetListView());
        }

        public void CloseView()
        {
            if (viewStack.Count == 0)
            {
                Close();
            }
            else
            {
                GadgetContent.Children.Remove(viewStack.Pop());
                GadgetContent.Children.Add(viewStack.Peek());
            }
        }

        public void StartView(UserControl view)
        {
            if (viewStack.Count > 0)
            {
                GadgetContent.Children.RemoveAt(0);
            }

            viewStack.Push(view);
            GadgetContent.Children.Add(view);
        }
    }
}
