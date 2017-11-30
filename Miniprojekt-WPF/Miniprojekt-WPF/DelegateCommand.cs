using System;
using System.Windows.Input;

namespace Miniprojekt_WPF
{
    public class DelegateCommand : ICommand
    {
        public event EventHandler CanExecuteChanged;
        public event Action OnExecute;

        public DelegateCommand(Action onExecute)
        {
            OnExecute = onExecute;
        }

        public bool CanExecute(object parameter)
        {
            return true;
        }

        public void Execute(object parameter)
        {
            OnExecute?.Invoke();
        }
    }
}
