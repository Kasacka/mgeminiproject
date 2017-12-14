using Miniprojekt_WPF;
using System;
using System.Windows.Controls;

namespace Miniprojekt_WPFTesting
{
    internal sealed class MockNavigationContext : INavigationContext
    {
        public void CloseView()
        {
        }

        public void StartView(UserControl view)
        {
        }
    }
}
