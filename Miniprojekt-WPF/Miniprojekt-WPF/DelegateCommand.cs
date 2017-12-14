using System;
using System.Windows.Input;

namespace Miniprojekt_WPF
{
    public class DelegateCommand : ICommand
    {
        public event Action OnExecuteFunc;
        public event Func<bool> CanExecuteFunc;


        public event EventHandler CanExecuteChanged
        {
            add { CommandManager.RequerySuggested += value; }
            remove { CommandManager.RequerySuggested -= value; }
        }

        public DelegateCommand(Action onExecute)
            : this(onExecute, null)
        {
            OnExecuteFunc = onExecute;
        }

        public DelegateCommand(Action onExecute, Func<bool> canExecute)
        {
            OnExecuteFunc = onExecute;
            CanExecuteFunc = canExecute;
        }

        public bool CanExecute(object parameter)
        {
            return CanExecuteFunc?.Invoke() ?? true;
        }

        public void Execute(object parameter)
        {
            OnExecuteFunc?.Invoke();
        }
    }
}
