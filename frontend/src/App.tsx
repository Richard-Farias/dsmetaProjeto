import NotificationButton from "./componets/NotificationButton"
import Header from "./componets/Header"
import SalesCard from "./componets/SalesCard"

function App() {
  return(
   // <> É usado para exportar mais de uma tag 
  <> 
      <Header/>
      <main>
        <section id="sales">
          <div className="dsmeta-container">
          <SalesCard/>
          </div>

        </section>

      </main>




  </> // </> É usado para exportar mais de uma tag
      )
 
}

export default App
