// importando imagem para a tag
import icon from '../../assets/img/notification-icon.png'
//importando css da página
import './styles.css'
function NotificationButton() {
    return( 
        <div className="dsmeta-red-btn">
            <img src={icon} alt="Notificar" />
        </div>
        )
  }
  
  export default NotificationButton 