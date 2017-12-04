using System.Windows.Controls;

namespace Miniprojekt_WPF
{
    public interface INavigationContext
    {
        void CloseView();
        void StartView(UserControl view);
    }
}
